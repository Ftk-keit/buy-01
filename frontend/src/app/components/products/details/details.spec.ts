import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Details} from './details';
import {provideHttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

describe('Details', () => {
    let component: Details;
    let fixture: ComponentFixture<Details>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Details],
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

        fixture = TestBed.createComponent(Details);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
