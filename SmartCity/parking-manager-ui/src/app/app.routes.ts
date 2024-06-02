import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { ParkingLotListComponent } from './components/parking-lot-list/parking-lot-list.component';
import { ParkingLotFormComponent } from './components/parking-lot-form/parking-lot-form.component';
import { UserPanelComponent } from './components/user-panel/user-panel.component';

export const routes: Routes = [
    {
        path:'',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'users',
        component: UserListComponent
    },
    {
        path:'parking-lots',
        component: ParkingLotListComponent
    },
    {
        path:'create-parking-lot',
        component: ParkingLotFormComponent
    },
    {
        path:'user-panel',
        component: UserPanelComponent
    },
];
