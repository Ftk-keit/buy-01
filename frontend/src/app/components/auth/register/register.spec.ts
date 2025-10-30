import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Register} from './register';
import {provideHttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

describe('Register', () => {
    let component: Register;
    let fixture: ComponentFixture<Register>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Register],
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

        fixture = TestBed.createComponent(Register);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
