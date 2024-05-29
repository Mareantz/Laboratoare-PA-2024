import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  user: User = { username: '', email: '', password: '' };

  constructor(private userService: UserService, private router: Router) { }

  register(): void {
    this.userService.register(this.user).subscribe(() => {
      this.router.navigate(['/login']);
    });
  }
}
