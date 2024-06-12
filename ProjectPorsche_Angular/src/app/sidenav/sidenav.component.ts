import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { NgIf, NgClass } from '@angular/common';

@Component({
  selector: 'app-sidenav',
  standalone: true,
  templateUrl: './sidenav.component.html',
  styleUrl: './sidenav.component.css',
  imports: [MatSidenavModule, MatToolbarModule, MatButtonModule, MatIconModule, MatListModule, RouterModule, NgIf, NgClass]
})

export class SidenavComponent {
  isMobile = false;
  isCollapsed = true;

  constructor(private breakpointObserver: BreakpointObserver) {}

  ngOnInit() {
    this.breakpointObserver.observe([Breakpoints.Handset]).subscribe(result => {
      this.isMobile = result.matches;
    });
  }

  toggleMenu() {
    if (this.isMobile) {
      this.isCollapsed = false;
    } else {
      this.isCollapsed = !this.isCollapsed;
    }
  }
}
