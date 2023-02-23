import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {properties} from "../../../resources/application.properties";

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  private host: string = properties.apiUrl;

  constructor(private http: HttpClient) { }

  /* Регистрация, аутентификация, сброс пароля */

  public registration(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/registrationUser`, user,
      {observe: 'response'});
  }

  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/confirmEmailUser`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(userEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/resetPasswordUser`, userEmail,
      {observe: 'response'});
  }


  public newPassword(userPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/newPasswordUser`, userPassword,
      {observe: 'response'});
  }

  public login(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/loginUser`, user,
      {observe: 'response'});
  }


  /*--------------------*/

  public addAnnouncement(messageBody: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/addAnnouncement`, messageBody,
      {observe: 'response'});
  }

  /*--------------------*/

  public getAnnouncement(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/getAnnouncement`,
      {observe: 'response'});
  }

  /*--------------------*/

  /* Тестовый */
  public userTestGetText(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/AccountGuard/user`);
  }


}
