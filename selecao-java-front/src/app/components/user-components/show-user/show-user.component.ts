import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../Entity/user';
import { UserService } from '../../../user.service';
import { ListUserComponent } from '../list-user/list-user.component';

@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styleUrls: ['./show-user.component.scss']
})
export class ShowUserComponent implements OnInit {
  paramLabel: string;
  id: number;
  user: User;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = new User();
    this.paramLabel = 'id';

    this.id = this.route.snapshot.params[this.paramLabel];

    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data);
        this.user = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['/users']);
  }

  edit(id: number){
    this.router.navigate(['user/update', id]);
  }

}
