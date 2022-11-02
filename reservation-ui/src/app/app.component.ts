import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Invited';

  public sliderArray: Array<object> = [{
      image: 'assets/images/slider1.jpg',
      thumbImage: 'assets/images/slider1.jpg',
      alt: 'Nature',
      title: 'Incredible Experience Await'
    }, {
      image: 'assets/images/slider2.jpg',
      thumbImage: 'assets/images/slider2.jpg',
      alt: 'Nature',
      title: 'Come, have fun'
    },
    {
      image: 'assets/images/slider3.jpg',
      thumbImage: 'assets/images/slider3.jpg',
      alt: 'Nature',
      title: 'Celebrate Togather'
    }
  ];
}
