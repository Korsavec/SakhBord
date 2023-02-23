import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {GeneralService} from "../../_service/general/general.service";
import {Announcement} from "../../model/announcement";
import {HttpRequestService} from "../../_service/http-request/http-request.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  announcements!: Announcement[];

  closeLogin: boolean = true

  isShowUserAccount: boolean = false;

  constructor(private generalService: GeneralService, private router: Router, private httpRequestService: HttpRequestService) {
  }

  ngOnInit(): void {

    this.isShowUserAccount = this.generalService.isTokenExpired('auth-user')
    this.closeLogin = !this.isShowUserAccount;


    this.httpRequestService.getAnnouncement().subscribe({
      next: data => {

        let responseData:any = data


        this.announcements = responseData.body;

        console.log(responseData.body)


      },
      error: err => {

      }
    });

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




}
