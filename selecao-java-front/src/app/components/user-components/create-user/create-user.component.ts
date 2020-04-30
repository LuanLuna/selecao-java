import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../../Entity/user';
import { UserService } from '../../../user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {
  user: User = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  newUser(): void {
    this.user = new User();
  }

  save() {
    this.userService.createUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    this.list();
  }

  onSubmit() {
    this.save();
  }

  list(){
    this.router.navigate(['/users']);
  }

}
