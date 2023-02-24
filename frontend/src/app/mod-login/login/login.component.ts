import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {GeneralService} from "../../_service/general/general.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup = this.formBuilder.group({email: [''],password: [''], checkbox: ['']});

  errorMessage!: string;

  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpRequestService: HttpRequestService, private localStorageService: LocalStorageService,
              private generalService: GeneralService) {
  }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {

      this.loginForm = this.formBuilder.group({
        email: ['', {
          validators: [
            Validators.required,
            Validators.pattern(this.validateService.patternEmail())
          ],
          updateOn: 'change'
        }],
        password: ['', {
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
        checkbox: [false],

      });

    }



  }

  get email() {
    return this.loginForm.controls['email'];
  }

  get password() {
    return this.loginForm.controls['password'];
  }

  get checkbox() {
    return this.loginForm.controls['checkbox'];
  }


  submit() {


    // Проверяем на блокировку пользователя при многократной ошибке входа
    if (this.localStorageService.getStorageItem('counterLogin') == '10') {
      this.errorMessage = 'Что-то пошло не так. Попробуйте позе';
      this.generalService.isBlockedLogin();
    } else {
      this.httpRequestService.loginUser(this.loginForm.value).subscribe({
        next: data => {

          let responseData:any;

          responseData = data;

          this.localStorageService.saveJwt(responseData.body.message);
          this.localStorageService.removeCount()
          this.goToPage();
        },
        error: err => {
          this.generalService.isBlockedLogin();
          this.errorMessage = err.error.message;
        }
      });
    }



  }

  deleteErrorMessage() {
    this.errorMessage = ''
  }

  goToPage() {
    this.router.navigate(['/']).then(() => {});
  }

  goToPageRegistration() {
    this.router.navigate(['/registration']).then(() => {});
  }

}
