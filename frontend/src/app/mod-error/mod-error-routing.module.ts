import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModErrorComponent} from './mod-error.component';

const routes: Routes = [{ path: '', component: ModErrorComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModErrorRoutingModule { }
