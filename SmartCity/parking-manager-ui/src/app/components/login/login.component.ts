import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: User = { username: '', email: '', password: '' };

  constructor(private userService: UserService, private router: Router) { }

  login(): void {
    this.userService.login(this.user).subscribe(response => {
      console.log('Login successful:', response);
      // Handle successful login, maybe store a token or navigate to another route
    });
  }
}
