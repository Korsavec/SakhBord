import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {



  public submitForm: boolean = true;

  public responseServer: boolean = true;

  public responseView: boolean = true;

  public emailExists: boolean = false;

  loginForm:FormGroup = this.formBuilder.group({email1: [''], email2: [''], password1: [''], password2: ['']});


  public editMail!:boolean;
  public editPass!:boolean;



  constructor(private validateService: ValidateService, private formBuilder: FormBuilder,
              private httpRequestService: HttpRequestService, private router: Router,
              private localStorageService: LocalStorageService) {
  }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      this.loginForm = this.formBuilder.group({
        email1: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail()),
          ],
          updateOn: 'change'
        }],
        email2: ['', {
          validators: [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(58),
            Validators.pattern(this.validateService.patternEmail()),
          ],
          updateOn: 'change'
        }],
        password1: ['', {
          validators: [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(24),
            Validators.pattern(this.validateService.patternPassword()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }],
        password2: ['', {
          validators: [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(24),
            Validators.pattern(this.validateService.patternPassword()),
          ],
          updateOn: 'change'
          /* change
             blur
             submit */
        }]
      }, {validators: this.matchPassword('email1', 'email2', 'password1', 'password2')});
    }


  }

  get email1() {
    return this.loginForm.controls['email1'];
  }

  get email2() {
    return this.loginForm.controls['email1'];
  }

  get password1() {
    return this.loginForm.controls['password1'];
  }

  get password2() {
    return this.loginForm.controls['password2'];
  }

  // Сверяем два пароля на одинаковость
  public matchPassword(email1: any, email2: any, password1: any, password2: any): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {


      if (this.editMail) {
        let mailOne = control.get(email1)?.value;
        let mailTwo = control.get(email2)?.value;

        if (mailOne != mailTwo && (mailOne.length >= 8 && mailTwo.length >= 8)) {
          return { 'noMatchMail': true }
        }
      } else if (this.editPass) {
        let passwordOne = control.get(password1)?.value;
        let passwordTwo = control.get(password2)?.value;

        if (passwordOne != passwordTwo && (passwordOne.length >= 6 && passwordTwo.length >= 6)) {
          return { 'noMatchPass': true }
        }
      }



      return null;

    }
  }

  submit() {

    const user: any = {
      email: null,
      password: null
    }

    user.email = this.email1.value;
    user.password = this.password1.value;

    this.submitForm = false;


    this.httpRequestService.registration(user).subscribe({
      next: () => {

        this.responseServer = false;

      },
      error:(err) => {

        if (err.error.status == 400
          && err.error.message == 'User exist') {

          this.emailExists = true;
          this.submitForm = true;
          this.responseServer  = true;

        } else {
          this.responseServer = false;
          this.responseView = false;
        }

      }
    });
  }


  changeFieldMail() {
    this.emailExists = false
    this.editMail = true
    this.editPass = false
  }


  changeFieldPass() {
    this.editMail = false
    this.editPass = true
  }


  goToPageLogin() {
    this.router.navigate(['/login']).then(() => {});
  }


}
