import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', loadChildren: './auth/auth.module#AuthModule'},
  {path: 'repositories', loadChildren: './employees/employees.module#EmployeesModule'},
  {path: 'chat', loadChildren: './chat/chat.module#ChatModule'},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
