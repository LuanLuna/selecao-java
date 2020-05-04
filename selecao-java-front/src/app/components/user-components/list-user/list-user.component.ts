import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../../../Entity/user';
import { UserService } from '../../../user.service';
import { ShowUserComponent } from '../show-user/show-user.component';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent implements OnInit {
  users: Observable<User[]>;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.users = this.userService.getUserList();
  }

  reloadData() {
    this.users = this.userService.getUserList();
  }
  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['user/details', id]);
  }

  newUser(){
    this.router.navigate(['user/create']);
  }

}
