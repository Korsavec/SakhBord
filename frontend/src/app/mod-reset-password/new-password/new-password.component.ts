import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {


  public submitForm: boolean = false;
  public responseServer: boolean = true;
  public responseView!: boolean;

  // Декларирую переменную чтобы не давала исключение. Например: Если зайти на страницу
  // http://localhost:4200/resetPassword/newPassword/eb0e8057-f0e1-424d-bca3-2fbbe204b1c8
  // и потом зайти на любую другую страницу, например страницу входа, а затем сделать назад,
  // то в консоли будет исключение потому что в переменной ничего нет.
  loginForm:FormGroup = this.formBuilder.group({password1: [''] ,password2: ['']});

  private token!: string;


  constructor(private validateService: ValidateService, private formBuilder: FormBuilder, private router: Router,
              private httpRequestService: HttpRequestService, private route: ActivatedRoute,
              private localStorageService: LocalStorageService) { }

  ngOnInit(): void {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {});

    } else {
      // Получаем токен из строки браузера
      this.route.paramMap.pipe(
        switchMap(params => params.getAll('token'))
      ).subscribe(data => this.token = data);


      this.httpRequestService.checkTokenUserResetPassword(this.token).subscribe({
        next: data => {

          let responseData:any;

          responseData = data;

          if (responseData.body.status == 400 && responseData.body.message == 'no') {
            this.router.navigate(['/']).then(() => {});
          } else {
            this.submitForm = true;
          }

        },
        error: () => {
          this.responseServer = false;
          this.responseView = false;
        }
      })


      if (this.validateService.patternToken(this.token)) {

        // Токен действителен

        // Валидация полей ввода на странице
        this.loginForm = this.formBuilder.group({
          password1: ['', {
            validators: [
              Validators.required,
              Validators.minLength(6),
              Validators.maxLength(24),
              Validators.pattern(this.validateService.patternPassword()),
            ],
            updateOn: 'change'
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
        }, {validators: this.matchPassword('password1', 'password2')});

      } else {
        // Токен недействителен
        this.router.navigate(['/']).then(() => {});
      }

    }



  }

  get password1() {
    return this.loginForm.controls['password1'];
  }

  get password2() {
    return this.loginForm.controls['password2'];
  }

  // Сверяем два пароля чтобы были одинаковы
  public matchPassword(password1: any, password2: any): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {

      let passwordOne = control.get(password1)?.value
      let passwordTwo = control.get(password2)?.value

      if (passwordOne != passwordTwo && (passwordOne.length >= 6 && passwordTwo.length >= 6)) {
        return { 'noMatch': true }
      }
      return null

    }
  }

  submit() {

    const userPassword: any = {
      password: null,
      token: null
    }

    userPassword.password = this.password1.value;
    userPassword.token = this.token;

    this.submitForm = false;

    this.httpRequestService.newPassword(userPassword).subscribe({
      next: () => {
        this.responseServer = false;
        this.responseView = true;
      },
      error: () => {

        this.responseServer = false;
        this.responseView = false;

      }
    });

  }


  goToPageRegistration() {
    this.router.navigate(['/registration']).then(() => {});
  }

}
