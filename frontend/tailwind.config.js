/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      spacing: {
        '25my': '1.563rem',
        '2my' : '0.125rem',
        '15my': '0.938rem',
        '20my': '1.25rem',
      },
      width: {
        '200my': '12.5rem',
        '430my': '26.875rem',
        '60my': '3.75rem',
        '100my': '6.25rem',
        '102my': '6.375rem',
        '240my': '15rem',
        '300my': '18.75rem',
        '45my': '2.813rem',
        '220my': '13.75rem',
        '30my': '1.875rem',
        '140my': '8.75rem',
      },
      height: {
        '60my': '3.75rem',
        '30my': '1.875rem',
        '45my': '2.813rem',
        '25my': '1.563rem',
        '530my': '33.125rem',
      },
      colors: {
        'green-dark-sea-wave-my': '#00A591',
        'dark-white-my': '#F5F5F5',
        'dark-white-my-hover': '#eaeaea',
        'hoverButtonMy': '#F1FFFD',
        'button-hover-color-blue-my': '#444AD7',
        dayColor: '#009898',
      },
      fontSize: {
        logo: '2.25rem',
        buttonIconSearch30: '1.875rem',
        '20my': '1.25rem',
        '16my': '1rem'
      },
      minWidth: {
        '160my': '10rem',
        '185my': '11.563rem',
        '220my': '13.75rem',
      },
      maxWidth: {
        '430my': '26.875rem',
        '200my': '12.5rem',
        '1010my': '63.125rem',
        '900my': '56.25rem',
        '220my': '13.75rem',
      },
      maxHeight: {
        '205my': '12.813rem',
      },
      minHeight: {
        '65my': '4.063rem',
      }
    },
  },
  plugins: [],
}
