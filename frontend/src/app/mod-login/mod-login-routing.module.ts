import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModLoginComponent} from './mod-login.component';
import {LoginComponent} from "./login/login.component";

const routes: Routes = [

  { path: '', component: ModLoginComponent, children: [
      { path: '', component: LoginComponent, pathMatch: 'full' }
    ] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModLoginRoutingModule { }
