import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { JwPaginationComponent } from 'jw-angular-pagination';
import { CreateUserComponent } from './components/user-components/create-user/create-user.component';
import { ShowUserComponent } from './components/user-components/show-user/show-user.component';
import { ListUserComponent } from './components/user-components/list-user/list-user.component';
import { EditUserComponent } from './components/user-components/edit-user/edit-user.component';
import { CreateHistoryItemComponent } from './components/history-item-components/create-history-item/create-history-item.component';
import { ShowHistoryItemComponent } from './components/history-item-components/show-history-item/show-history-item.component';
import { EditHistoryItemComponent } from './components/history-item-components/edit-history-item/edit-history-item.component';
import { ListHistoryItemComponent } from './components/history-item-components/list-history-item/list-history-item.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    ShowUserComponent,
    ListUserComponent,
    EditUserComponent,
    // JwPaginationComponent,
    CreateHistoryItemComponent,
    ShowHistoryItemComponent,
    EditHistoryItemComponent,
    ListHistoryItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
