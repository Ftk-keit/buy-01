import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Login} from './login';
import {provideRouter, RouterLink} from '@angular/router';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';
import {ToastComponent} from '../../toast/toast.component';
import {AuthService} from '../../../services/auth.service';
import {AuthStateService} from '../../../services/auth.state.service';
import {ToastService} from '../../../services/toast.service';
import {provideHttpClient, withFetch} from '@angular/common/http';
import {provideNoopAnimations} from '@angular/platform-browser/animations';


describe('Login', () => {
    let component: Login;
    let fixture: ComponentFixture<Login>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                Login
            ],
            providers: [
                provideNoopAnimations(),
                provideHttpClient(withFetch()),
                provideRouter([]),
            ]
        })
            .compileComponents();

        fixture = TestBed.createComponent(Login);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
