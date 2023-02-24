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

    if (request.url.includes(`${this.host}/api/auth/registrationUser`)
      || request.url.includes(`${this.host}/api/auth/confirmEmailUser`)
      || request.url.includes(`${this.host}/api/auth/resetPasswordUser`)
      || request.url.includes(`${this.host}/api/auth/checkTokenUserResetPassword`)
      || request.url.includes(`${this.host}/api/auth/newPasswordUser`)
      || request.url.includes(`${this.host}/api/auth/loginUser`)
      || request.url.includes(`${this.host}/api/all`)
      || request.url.includes(`${this.host}/resources/all`)
      || request.url.includes(`${this.host}/resources/ResourcesGuard/image/**`)) {
      return next.handle(request);
    }



    // Для auth-user
    if (request.url.includes(`${this.host}/api/AccountGuard/user`)
      || request.url.includes(`${this.host}/api/AccountGuard/addAnnouncement`)) {

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

      } else if (request.url.includes(`${this.host}/api/data/announcement`)) {

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
