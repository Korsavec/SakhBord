import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class GeneralService {


  private _filterCategory!: string;
  private _filterCity!: string;

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


  get filterCategory(): string {
    return this._filterCategory;
  }

  set filterCategory(value: string) {
    this._filterCategory = value;
  }


  get filterCity(): string {
    return this._filterCity;
  }

  set filterCity(value: string) {
    this._filterCity = value;
  }




  isCategoryOfType(value:string): boolean {

    let cat: boolean = false;

    if (value === '110'
      || value === '121'
      || value === '132'
      || value === '190') {
      cat = true;
    }

    return cat;
  }



  isCategoryFull(value:string): boolean {

    let cat: boolean = false;

    if (value === '101'
      || value === '102'
      || value === '103'
      || value === '104'
      || value === '105'
      || value === '106'
      || value === '110'
      || value === '111'
      || value === '112'
      || value === '121'
      || value === '132'
      || value === '141'
      || value === '142'
      || value === '151'
      || value === '152'
      || value === '161'
      || value === '162'
      || value === '171'
      || value === '172'
      || value === '181'
      || value === '182'
      || value === '190') {
      cat = true;
    }

    return cat;
  }

  isCity(value:string) {

    let city: boolean = false;

    if (value === '1'
      || value === '2'
      || value === '3'
      || value === '4'
      || value === '5'
      || value === '6'
      || value === '7'
      || value === '8'
      || value === '9'
      || value === '10'
      || value === '11'
      || value === '12'
      || value === '13'
      || value === '14'
      || value === '15'
      || value === '16'
      || value === '17'
      || value === '18'
      || value === '19'
      || value === '20'
      || value === '21'
      || value === '22'
      || value === '23'
      || value === '24'
      || value === '25'
      || value === '26') {
      city = true;
    }

    return city;

  }

  isCategoryWithType(value:string) {

    let cat: boolean = false;

    if (value === '101'
      || value === '102'
      || value === '103'
      || value === '104'
      || value === '105'
      || value === '106'
      || value === '111'
      || value === '112'
      || value === '141'
      || value === '142'
      || value === '151'
      || value === '152'
      || value === '161'
      || value === '162'
      || value === '171'
      || value === '172'
      || value === '181'
      || value === '182') {
      cat = true;
    }

    return cat;
  }


}
