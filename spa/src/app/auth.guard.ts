import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private http: HttpClient) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    this.http.get('http://localhost:8080/api/login', { withCredentials: true }).subscribe(this.proceedUser);
    return true;
  }

  private proceedUser = (user: any) => {
    if (user == null) {
      this.router.navigate(['/login']);
    }
  };


}
