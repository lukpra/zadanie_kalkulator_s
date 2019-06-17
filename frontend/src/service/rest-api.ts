import axios from 'axios';

export class RestAPI {
    public static URL: string = 'ENDPOINT_FULL_URL';

    public static async getNetto(dailyWage: number, countryCode: string) {
        return await axios.get(RestAPI.URL, {
            params: {
                dailyWage,
                countryCode,
            },
        });
    }
}

