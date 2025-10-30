import {TestBed} from '@angular/core/testing';
import {CanActivateFn} from '@angular/router';
import {AuthGuard} from './auth.guard';
import {provideHttpClient} from '@angular/common/http';



describe('AuthGuard', () => {
    let guard: AuthGuard;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers:[
                provideHttpClient()
            ]
        });
        guard = TestBed.inject(AuthGuard);
    });

    it('devrait être créé', () => {
        expect(guard).toBeTruthy();
    });
});

