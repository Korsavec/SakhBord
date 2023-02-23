import {Component, OnInit} from '@angular/core';
import {ValidateService} from "../../_service/validation/validate.service";
import {HttpRequestService} from "../../_service/http-request/http-request.service";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-confirm-email',
  templateUrl: './confirm-email.component.html',
  styleUrls: ['./confirm-email.component.css']
})
export class ConfirmEmailComponent implements OnInit {

  private token: string = '';

  public responseServer: boolean = true;

  public responseConfirmMessage!: boolean;

  constructor(private validateService: ValidateService, private httpRequestService: HttpRequestService, private route: ActivatedRoute,
              private router: Router, private localStorageService: LocalStorageService) {
  }


  ngOnInit() {

    if (this.localStorageService.isTokenExpired()) {

      this.router.navigate(['/']).then(() => {
      });

    } else {

      // Получаем токен из строки браузера
      this.route.paramMap.pipe(
        switchMap(params => params.getAll('token'))
      ).subscribe(data => this.token = data);


      if (this.validateService.patternToken(this.token)) {

        this.httpRequestService.confirmEmail(this.token).subscribe({

          next: (value) => {


            if (!(value instanceof HttpErrorResponse)) {

              if (value.body.status === 404 || value.body.status === 400) {
                this.router.navigate(['/login']).then(() => {});
              }

            }



            this.responseServer = false;

            this.responseConfirmMessage = true;

          },

          error: () => {

            this.responseServer = false;

            this.responseConfirmMessage = false;

          }
        });

      } else {
        // fake
        setTimeout(() => this.fakeMessage(), 3000);
      }

    }


  }

  fakeMessage() {

    this.responseServer = false;

    this.responseConfirmMessage = true;
  }


}
