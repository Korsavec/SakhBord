import {Component, OnInit} from '@angular/core';
import {switchMap} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {GeneralService} from "../../_service/general/general.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Announcement} from "../../model/announcement";
import {HttpRequestService} from "../../_service/http-request/http-request.service";

@Component({
  selector: 'app-filter-ad',
  templateUrl: './filter-ad.component.html',
  styleUrls: ['./filter-ad.component.css']
})
export class FilterAdComponent implements OnInit {

  // Переменные в которых хранятся id пути из браузерной строки
  categoryPath!: string;
  cityPath!: string;



  closeLogin: boolean = true

  isShowUserAccount: boolean = false;

  formFilter:FormGroup = this.formBuilder.group({category: [''], city: ['']});

  announcements!: Announcement[];

  constructor(private httpRequestService: HttpRequestService, private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute, private router: Router,
              private generalService: GeneralService) {
  }

  ngOnInit(): void {


    this.isLogin();



    // Получаем id категорию из строки браузера
    this.activatedRoute.paramMap.pipe(
      switchMap(params => params.getAll('category'))
    ).subscribe(data => this.categoryPath = data);

    // Получаем id города из строки браузера
    this.activatedRoute.paramMap.pipe(
      switchMap(params => params.getAll('city'))
    ).subscribe(data => this.cityPath = data);





    // Валидация и вставляем пути в этот селектор
    this.formFilter = this.formBuilder.group({
      category: [this.categoryPath, {
        validators: [
          Validators.required],
        updateOn: 'change'
        /* change
           blur
           submit */
      }],
      city: [this.cityPath, {
        validators: [
          Validators.required],
        updateOn: 'change'
        /* change
           blur
           submit */
      }]

    });



    // Если код категории 110, 121, 132, 190 то, есть, категория не имеет тип категории, то выполняем:
    if (this.generalService.isCategoryOfType(this.category.value) && this.generalService.isCity(this.city.value)) {


      // Это для AuthInterceptor
      this.authInterceptor();


      this.httpRequestService.getAdOfType(this.category.value, this.city.value).subscribe({
        next: data => {

          let responseData:any = data

          this.announcements = responseData.body.content;


        }
      });

      // Если код категории имеет тип категории, то выполняем:
    } else if (this.generalService.isCategoryWithType(this.category.value) && this.generalService.isCity(this.city.value)) {

      // Это для AuthInterceptor
      this.authInterceptor();

      this.httpRequestService.getAdWithType(this.category.value, this.city.value).subscribe({
        next: data => {

          let responseData:any = data

          this.announcements = responseData.body.content;


        }
      });

    } else {
      this.router.navigate(['/']).then(() => {
      });
    }



  }






  get category() {
    return this.formFilter.controls['category'];
  }

  get city() {
    return this.formFilter.controls['city'];
  }




  // Получение объявлений через фильтр
  submit() {

    this.isLogin();

    // Если код категории 110, 121, 132, 190 то, есть, категория не имеет тип категории, то выполняем:
    if (this.generalService.isCategoryOfType(this.category.value)) {

      // Это для AuthInterceptor
      this.authInterceptor();


      this.httpRequestService.getAdOfType(this.category.value, this.city.value).subscribe({
        next: data => {

          let responseData:any = data

          this.announcements = responseData.body.content;

        }
      });

      this.router.navigate([`/filter/${this.category.value}/${this.city.value}`]).then(() => {
      });

      // Если код категории имеет тип категории, то выполняем:
    } else if (this.generalService.isCategoryWithType(this.category.value)) {




      // Это для AuthInterceptor
      this.authInterceptor();


      this.httpRequestService.getAdWithType(this.category.value, this.city.value).subscribe({
        next: data => {

          let responseData:any = data

          this.announcements = responseData.body.content;

        }
      });

      this.router.navigate([`/filter/${this.category.value}/${this.city.value}`]).then(() => {
      });

    }


  }



  goToPageLogin() {
    this.router.navigate(['/login']).then(() => {});
  }

  goToPageRegistration() {
    this.router.navigate(['/registration']).then(() => {});
  }

  goToPageAdd() {
    this.router.navigate(['/add']).then(() => {});
  }

  logOut() {

    this.closeLogin = true;
    this.isShowUserAccount = false;
    this.generalService.removeStorageItem('auth-user');

  }

  authInterceptor() {

    // Это для AuthInterceptor
    this.generalService.filterCategory = this.category.value;
    this.generalService.filterCity = this.city.value

  }


  isLogin() {
    this.isShowUserAccount = this.generalService.isTokenExpired('auth-user')
    this.closeLogin = !this.isShowUserAccount;
  }


}
