import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {properties} from "../../../resources/application.properties";
import {LocalStorageService} from "../../_service/local-storage/local-storage.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private host: string = properties.apiUrl;

  constructor(private localStorageService: LocalStorageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (request.url.includes(`${this.host}/api/registrationUser`)
      || request.url.includes(`${this.host}/api/confirmEmailUser`)
      || request.url.includes(`${this.host}/api/resetPasswordUser`)
      || request.url.includes(`${this.host}/api/checkTokenUserResetPassword`)
      || request.url.includes(`${this.host}/api/newPasswordUser`)
      || request.url.includes(`${this.host}/api/loginUser`)
      || request.url.includes(`${this.host}/api/loginAdmin`)
      || request.url.includes(`${this.host}/api/data/announcement`)
      || request.url.includes(`${this.host}/api/data/rules`)) {
      return next.handle(request);
    }


    // request.url.includes(`${this.host}/api/auth/admin`

    // Для auth-user
    if (request.url.includes(`${this.host}/api/auth/addAnnouncement`)) {

      const token = this.localStorageService.getStorageItem('auth-user');

      if (token) {
        const modifiedQuery = request.clone({
          setHeaders: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': `application/json`,
            'Accept': `application/json`,
          }
        });

        return next.handle(modifiedQuery);

      } else if (request.url.includes(`${this.host}/api/auth/admin`)) {

        const token = this.localStorageService.getStorageItem('auth-admin');

        if (token) {

          const modifiedQuery = request.clone({
            setHeaders: {
              'Authorization': `Bearer ${token}`,
              'Content-Type': `application/json`,
              'Accept': `application/json`,
            }
          });

          return next.handle(modifiedQuery);

        }




      } else if (request.url.includes(`${this.host}/api/resetPasswordUser`)) {

        const modifiedQuery = request.clone({
          setHeaders: {
            'Content-Type': `application/json`,
            'Accept': `application/json`,
          }
        });

        return next.handle(modifiedQuery);

      } else {

        return next.handle(request);

      }
    }

    return next.handle(request);

  }

}
