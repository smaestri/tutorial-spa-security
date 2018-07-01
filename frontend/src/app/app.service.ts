import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  isAuthenticated(){
    this.http.get('isAuthenticated').subscribe((response : boolean) => {
        this.authenticated = response;
    });
  }

  authenticate(credentials, callback) {
        if (!credentials)
          return;

        this.http.post('login', {username: credentials.username, password:  credentials.password} /*{headers: headers}*/).subscribe((response : boolean) => {
            this.authenticated = response;
            if (this.authenticated) {
                return callback && callback();
            }
        });
    }

}
