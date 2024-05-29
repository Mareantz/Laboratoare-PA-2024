// src/app/user-list/user-list.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { CommonModule, NgForOf } from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone:true,
  imports:[CommonModule,NgForOf],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id: number | undefined): void {
    if (id !== undefined) {
      this.userService.deleteUser(id).subscribe(() => {
        this.getUsers();
      });
    }
  }
}
