import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeesDashbordComponent} from './employees-dashbord/employees-dashbord.component';
import {DepartmentsDashbordComponent} from './departments-dashbord/departments-dashbord.component';
import {PositionsDashbordComponent} from './positions-dashbord/positions-dashbord.component';
import {AuthGuard} from '../auth.guard';

const routes: Routes = [
  {path: '', redirectTo: 'employees'},
  {path: 'employees', component: EmployeesDashbordComponent, canActivate: [AuthGuard]},
  {path: 'departments', component: DepartmentsDashbordComponent, canActivate: [AuthGuard]},
  {path: 'positions', component: PositionsDashbordComponent, canActivate: [AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule {
}
