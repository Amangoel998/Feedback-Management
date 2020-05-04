import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'feedback-system-frontend';
  
  navigateToWebsite(){
    document.location.href = "https://capgemini.com"
  }
}
