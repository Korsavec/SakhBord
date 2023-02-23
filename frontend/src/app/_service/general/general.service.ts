import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  blocked: boolean = false;

  jwtHelper = new JwtHelperService();


  public isTokenExpired(typeToken: string): boolean {

    let tokenStorage = this.getStorageItem(typeToken)!;

    if (this.getStorageItem(typeToken) === null) {
      // Токена нет
      return false;
    } else {
      if (this.jwtHelper.isTokenExpired(tokenStorage)) {
        // Токен истёк
        this.removeStorageItem(typeToken);
        return false;
      } else {
        // Токен не истёк
        return true;
      }
    }
  }


  public removeStorageItem(key: string) {
    return window.localStorage.removeItem(key);
  }

  public getStorageItem(key: string):string | null {
    return window.localStorage.getItem(key);
  }




  // Реализация блокировки пользователя при многократной ошибке входа
  public isBlockedLogin(): void {

    const time = this.getStorageItem('timeLogin');
    const counter = this.getStorageItem('counterLogin');

    let timeBoolean: boolean;
    let timeData: number;

    let counterData: number;

    if (time != null) {
      timeBoolean = true
      timeData = parseInt(time);
    } else {
      timeBoolean = false
      timeData = NaN;
    }

    if (counter != null) {
      counterData = parseInt(counter);
    } else {
      counterData = 0;
    }


    if (timeBoolean) { // Пользователь заблокирован если true
      if (timeData <= new Date().getTime()) {
        window.localStorage.removeItem("timeLogin");
        window.localStorage.setItem("counterLogin", String(1));
        this.blocked = false; // Пользователь не заблокирован
      } else {
        this.blocked = true; // Пользователь заблокирован
      }
    } else { // Пользователь не заблокирован
      if (counterData === 9) {
        window.localStorage.setItem("counterLogin", (counterData+1).toString());
        window.localStorage.setItem('timeLogin', (new Date().getTime()+3600000).toString()); // 1 час
        this.blocked = false; // Пользователь не заблокирован
      } else {
        window.localStorage.setItem("counterLogin", (counterData+1).toString());
        this.blocked = false; // Пользователь не заблокирован
      }
    }

  }



  // Подсчёт поданных объявлений в сутки
  public countingAddedAdsPerDay(): boolean {



    let countAdsTimeOne = this.getStorageItem('count-ads-time-one')!;
    let countAdsTimeTwo = this.getStorageItem('count-ads-time-two')!;
    let countAdsTimeThree = this.getStorageItem('count-ads-time-three')!;
    let countAdsTimeFour = this.getStorageItem('count-ads-time-four')!;


    // Удаляем если время истекло
    if ((countAdsTimeOne != null) && ((parseInt(countAdsTimeOne) + 86400000) <= (new Date().getTime()))) {
      window.localStorage.removeItem("count-ads-time-one");
    }

    if ((countAdsTimeTwo != null) && ((parseInt(countAdsTimeTwo) + 86400000) <= (new Date().getTime()))) {
      window.localStorage.removeItem("count-ads-time-two");
    }

    if ((countAdsTimeThree != null) && ((parseInt(countAdsTimeThree) + 86400000) <= (new Date().getTime()))) {
      window.localStorage.removeItem("count-ads-time-three");
    }

    if ((countAdsTimeFour != null) && ((parseInt(countAdsTimeFour) + 86400000) <= (new Date().getTime()))) {
      window.localStorage.removeItem("count-ads-time-four");
    }






    if (countAdsTimeOne === null) {
      window.localStorage.setItem("count-ads-time-one", new Date().getTime().toString());
      return true;
    } else if (countAdsTimeTwo === null) {
      window.localStorage.setItem("count-ads-time-two", new Date().getTime().toString());
      return true;
    } else if (countAdsTimeThree === null) {
      window.localStorage.setItem("count-ads-time-three", new Date().getTime().toString());
      return true;
    } else if (countAdsTimeFour === null) {
      window.localStorage.setItem("count-ads-time-four", new Date().getTime().toString());
      return true;
    } else {
      return false;
    }



  }

}
