import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Sidebar} from './sidebar';
import {provideHttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

describe('Sidebar', () => {
    let component: Sidebar;
    let fixture: ComponentFixture<Sidebar>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Sidebar],
            providers:[
                provideHttpClient(),
                {
                    provide: ActivatedRoute,
                    useValue: {
                        snapshot: { paramMap: { get: () => null } },
                    },
                },
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(Sidebar);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
