import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {keyframes} from "@angular/animations";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  host : string = "localhost:8082/";
  token : any;
  constructor(private httpClient: HttpClient, private route: Router) {

  }
  public getToken(){
    var body : any =  {
      "username":"kouhou@manager.com",
      "password":1234,
      "client_id":"blockchain-microservice",
      "grant_type":"password"
    }
    this.httpClient.post("http://localhost:8090/auth/realms/blockchain_realm/protocol/openid-connect/token", body).subscribe(
      (data)=>{
        // @ts-ignore
        this.token = data['access-token'];
      }
    )
  }
  public listOfBlockchains(){
    var requestHeader = new HttpHeaders()
      .append('content-Type', 'application/json')
      .append('authorization', 'Bearer '+this.token);
    var url = this.host+"all";
    return this.httpClient.get(url,{headers: requestHeader} )
  }
}
