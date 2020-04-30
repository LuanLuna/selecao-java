import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CreateUserComponent } from './components/user-components/create-user/create-user.component';
import { EditUserComponent } from './components/user-components/edit-user/edit-user.component';
import { ListUserComponent } from './components/user-components/list-user/list-user.component';
import { ShowUserComponent } from './components/user-components/show-user/show-user.component';
import {ListHistoryItemComponent} from './components/history-item-components/list-history-item/list-history-item.component';
import {CreateHistoryItemComponent} from './components/history-item-components/create-history-item/create-history-item.component';
import {EditHistoryItemComponent} from './components/history-item-components/edit-history-item/edit-history-item.component';
import {ShowHistoryItemComponent} from './components/history-item-components/show-history-item/show-history-item.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'users', component: ListUserComponent },
  { path: 'user/create', component: CreateUserComponent },
  { path: 'user/update/:id', component: EditUserComponent },
  { path: 'user/details/:id', component: ShowUserComponent },
  { path: 'history', component: ListHistoryItemComponent },
  { path: 'history/create', component: CreateHistoryItemComponent },
  { path: 'history/update/:id', component: EditHistoryItemComponent },
  { path: 'history/details/:id', component: ShowHistoryItemComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
