import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModAddComponent} from './mod-add.component';
import {AddComponent} from "./add/add.component";

const routes: Routes = [

  { path: '', component: ModAddComponent, children: [
      {path: '', component: AddComponent, pathMatch: 'full'}
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModAddRoutingModule { }
