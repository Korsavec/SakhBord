import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../model/announcement";
import {GeneralService} from "../../_service/general/general.service";
import {Router} from "@angular/router";
import {HttpRequestService} from "../../_service/http-request/http-request.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


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
