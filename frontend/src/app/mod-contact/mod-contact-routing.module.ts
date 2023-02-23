import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModContactComponent} from './mod-contact.component';
import {ContactComponent} from "./contact/contact.component";

const routes: Routes = [

  { path: '', component: ModContactComponent, children: [
      {path: '', component: ContactComponent, pathMatch: 'full'}
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModContactRoutingModule { }
