import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {GeneralService} from "../../_service/general/general.service";
import {Announcement} from "../../model/announcement";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {


  formFilter:FormGroup = this.formBuilder.group({category: [''], city: ['']});

  announcements!: Announcement[];

  closeLogin: boolean = true

  isShowUserAccount: boolean = false;

  constructor(private formBuilder: FormBuilder, private generalService: GeneralService,
              private router: Router, private httpRequestService: HttpRequestService) {
  }

  ngOnInit(): void {

    this.formFilter = this.formBuilder.group({
      category: ['', {
        validators: [
          Validators.required],
        updateOn: 'change'
        /* change
           blur
           submit */
      }],
      city: ['', {
        validators: [
          Validators.required],
        updateOn: 'change'
        /* change
           blur
           submit */
      }]

    });


    this.isLogin();


    this.httpRequestService.getAnnouncement().subscribe({
      next: data => {

        let responseData:any = data

        this.announcements = responseData.body;


      }
    });

  }





  get category() {
    return this.formFilter.controls['category'];
  }

  get city() {
    return this.formFilter.controls['city'];
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






  // Получение объявлений через фильтр
  submitFilter() {

    this.isLogin();

    if (this.generalService.isCategoryFull(this.category.value) && this.generalService.isCity(this.city.value)) {

      this.router.navigate([`/filter/${this.category.value}/${this.city.value}`]).then(() => {
      });

    } else {
      this.router.navigate(['/']).then(() => {
      });
    }


  }

  isLogin() {
    this.isShowUserAccount = this.generalService.isTokenExpired('auth-user')
    this.closeLogin = !this.isShowUserAccount;
  }

}
