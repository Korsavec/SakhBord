import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {Rules} from "../../model/rules";

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {

  rules!: Rules[];

  constructor(private router: Router, private httpRequestService: HttpRequestService) {
  }



  ngOnInit(): void {


    this.httpRequestService.getRules().subscribe({
      next: data => {

        let responseData:any = data

        this.rules = responseData.body;

      },
      error: err => {

      }
    });

  }


  goToPageRegistration() {
    this.router.navigate(['/registration']).then(() => {});
  }

}
