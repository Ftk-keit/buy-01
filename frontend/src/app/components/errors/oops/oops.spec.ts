import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Oops} from './oops';
import {ActivatedRoute} from '@angular/router';

describe('Oops', () => {
    let component: Oops;
    let fixture: ComponentFixture<Oops>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Oops],
            providers:[
                {
                    provide: ActivatedRoute,
                    useValue: {
                        snapshot: { paramMap: { get: () => null } },
                    },
                },
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(Oops);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
