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
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/registrationUser`, user,
      {observe: 'response'});
  }

  public confirmEmail(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/confirmEmailUser`,
      {"token": token}, {observe: 'response'});
  }

  public resetPassword(userEmail: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/resetPasswordUser`, userEmail,
      {observe: 'response'});
  }

  public checkTokenUserResetPassword(token: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/checkTokenUserResetPassword`,
      {"token": token}, {observe: 'response'});
  }


  public newPassword(userPassword: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/newPasswordUser`, userPassword,
      {observe: 'response'});
  }

  public loginUser(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/loginUser`, user,
      {observe: 'response'});
  }


  /*--------------------*/

  public addAnnouncement(messageBody: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/addAnnouncement`, messageBody,
      {observe: 'response'});
  }

  /*--------------------*/

  public getAnnouncement(): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.get<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/data/announcement`,
      {observe: 'response'});
  }

  /*--------------------*/

  public loginAdmin(user: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/loginAdmin`, user,
      {observe: 'response'});
  }

  public admin(admin: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/api/auth/admin`, admin,
      {observe: 'response'});
  }

  /*--------------------*/

}
