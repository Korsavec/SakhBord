import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModRegistrationComponent} from './mod-registration.component';
import {RegistrationComponent} from "./registration/registration.component";
import {ConfirmEmailComponent} from "./confirm-email/confirm-email.component";

const routes: Routes = [

  { path: '', component: ModRegistrationComponent, children: [
      {path: '', component: RegistrationComponent, pathMatch: 'full'}
    ] },

  { path: 'confirmEmail/:token', component: ConfirmEmailComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModRegistrationRoutingModule { }
