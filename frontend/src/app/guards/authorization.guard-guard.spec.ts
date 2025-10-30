import { TestBed } from '@angular/core/testing';
import { AuthorizationGuard } from './authorization.guard';
import {provideHttpClient} from '@angular/common/http';

describe('AuthorizationGuard', () => {
    let guard: AuthorizationGuard;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers:[
                provideHttpClient()
            ]
        });
        guard = TestBed.inject(AuthorizationGuard);
    });

    it('devrait être créé', () => {
        expect(guard).toBeTruthy();
    });
});
