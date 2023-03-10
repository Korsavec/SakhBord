import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {Router} from "@angular/router";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {GeneralService} from "../../_service/general/general.service";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {


  viewForm = true;

  setEmailInput = '';


  loginForm!:FormGroup;

  phoneMaxLength:number = 10;

  emailAddressMinLength:number = 8;
  emailAddressMaxLength:number = 58;

  telegramMinLength:number = 5;
  telegramMaxLength:number = 32;

  maxMessage:number = 335;
  minMessage:number = 120;

  count:number = 0;
  countMin:number = 0;




  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpRequestService: HttpRequestService, private localStorageService: LocalStorageService,
              private generalService: GeneralService) {
  }

  ngOnInit(): void {

    if (!this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {



      // Получаем email и вставляем в input для отображения
      let tokenStorage = this.localStorageService.getStorageItem('auth-user')!;
      const jwtHelper = new JwtHelperService();
      this.setEmailInput = jwtHelper.decodeToken(tokenStorage).email;





      this.loginForm = this.formBuilder.group({
        message: ['', {
          validators: [
            Validators.required,
            Validators.minLength(120),
            Validators.maxLength(335),
            Validators.pattern(this.validateService.patternMessage()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        selectCity: ['', {
          validators: [
            Validators.required,
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        selectDepartament: ['', {
          validators: [
            Validators.required,
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        phoneNumber: ['', {
          validators: [
            Validators.minLength(10),
            Validators.maxLength(10),
            Validators.pattern(this.validateService.patternOnlyNumbersRegExp()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        emailAddress: [this.setEmailInput, {
          validators: [
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        telegram: ['', {
          validators: [
            Validators.minLength(5),
            Validators.maxLength(32),
            Validators.pattern(this.validateService.patternTelegram()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }]

      }, {validators: this.matchMessage('message','phoneNumber','emailAddress','telegram')});

    }


  }



  get message() {
    return this.loginForm.controls['message'];
  }

  get selectCity() {
    return this.loginForm.controls['selectCity'];
  }

  get selectDepartament() {
    return this.loginForm.controls['selectDepartament'];
  }

  get phoneNumber() {
    return this.loginForm.controls['phoneNumber'];
  }

  get emailAddress() {
    return this.loginForm.controls['emailAddress'];
  }

  get telegram() {
    return this.loginForm.controls['telegram'];
  }






  public matchMessage(message: any, phoneNumber:any, emailAddress:any, telegram:any): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {


      let messageLength = control.get(message)?.value;

      this.count = 335 - messageLength.length


      if ((120 - messageLength.length) < 1) {
        this.countMin = 0;
      } else {
        this.countMin = 120 - messageLength.length
      }


      let phone = control.get(phoneNumber)?.value;
      let email = control.get(emailAddress)?.value;
      let garm = control.get(telegram)?.value;





      if (!(this.validateService.phoneNumbersRegExp(phone) && (phone.length === 10))
        && !(this.validateService.patternEmailTest(email) && !(email === '' || email.length < 8 || email.length > 58))
        && !(this.validateService.patternTelegramTest(garm) && !(garm === '' || garm.length < 5 || garm.length > 32))) {

        return { 'selectList': true }
      }

      return null;

    }
  }



  submit() {

    if (this.generalService.countingAddedAdsPerDay()) {


      this.viewForm = false;


      const messageBody: any = {
        message: null,
        selectCity: null,
        selectDepartament: null,
        phoneNumber: null,
        emailAddress: null,
        telegram: null,
      }

      messageBody.message = this.message.value;
      messageBody.selectCity = this.selectCity.value;
      messageBody.selectDepartament = this.selectDepartament.value;
      messageBody.phoneNumber = this.phoneNumber.value;
      messageBody.emailAddress = this.emailAddress.value;
      messageBody.telegram = this.telegram.value;


      this.httpRequestService.addAnnouncement(messageBody).subscribe({
        next: () => {
          this.router.navigate(['/']).then(() => {});

        },
        error:() => {

          this.router.navigate(['/']).then(() => {});

        }
      });


    } else {
      this.router.navigate(['/']).then(() => {});
    }


  }


  logOut() {

    this.generalService.removeStorageItem('auth-user');

  }


}
