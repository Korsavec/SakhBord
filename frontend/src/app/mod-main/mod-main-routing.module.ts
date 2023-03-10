import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModMainComponent} from './mod-main.component';
import {MainComponent} from "./main/main.component";
import {FilterAdComponent} from "./filter-ad/filter-ad.component";

const routes: Routes = [

  { path: '', component: ModMainComponent, children:[
      { path: '', component: MainComponent, pathMatch: 'full' },
    ] },

  { path: 'filter/:category', children: [
      {path: ':city', component: FilterAdComponent}
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModMainRoutingModule { }
