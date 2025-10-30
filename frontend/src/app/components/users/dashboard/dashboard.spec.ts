import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Dashboard} from './dashboard';
import {provideHttpClient} from '@angular/common/http';

describe('Dashboard', () => {
    let component: Dashboard;
    let fixture: ComponentFixture<Dashboard>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Dashboard],
            providers:[
                provideHttpClient()
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(Dashboard);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
