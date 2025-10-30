import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Add} from './add';
import {provideHttpClient} from '@angular/common/http';

describe('Add', () => {
    let component: Add;
    let fixture: ComponentFixture<Add>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [Add],
            providers:[
                provideHttpClient()
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(Add);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
