import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 10,
    duration: '5s',
};

export default function () {
    while (true) {
        http.get('http://localhost:8881/send');
        sleep(0.01);
    }
}
