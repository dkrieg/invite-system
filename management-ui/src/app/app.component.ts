import { Component, OnInit } from '@angular/core';
import { SharedService } from './shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public isMenuOpen: boolean = false;
  title = 'management-ui';
  public menuData: any = [];

  constructor(public sharedService: SharedService) {}

  ngOnInit(): void {
    this.getSideMenuData();
  }

  getSideMenuData() {
    const url = 'assets/dynamic.menu.json';
    this.sharedService.fetchSideMenuData(url).subscribe(
      (data) => (this.menuData = data),
      (error) => console.log('error ==> ', error)
    );
  }
}
