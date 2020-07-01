# Ecommerce application for Hiberus

Welcome to e-commerce simulate for hiberus Test for Java Microservices.

## Technologies and tools:

- Java version "1.8.0_241"
- Spring tools suite 4 (IDE)
- Spring boot 2.3.1
- Spring Data
- Maven
- Docker version 19.03.8
- Docker-compose version 1.25.5
- Swagger 2.7.0
- User SO: Windows 10 Pro
- Git version 2.26.0
- Postgres Database
- Postman


 ## This project has six Microservices:

- **CheckOutService:**  (./checkoutecommerce folder): This service begin the process and call Bill service and Logistic service
- **CheckOutService Database:** This services is a postgres database that include Clients info
- **BillService:** (./billecommerce folder): This service receive Date, ClientId, Products info and get sum of products values
- **BillService Database:** : This services is another postgres database that include products info. (This database is diferent that Checkout Database)
- **Logisticservice:** (./logisticecommerce): This services is responsible of generation of sent order. It create a number Id and return this value in an OutLogisticVO object, also create a register in Order table with the Sent order info.
- **LogisticService Database:**: This services is another postgres database that include order info. (This database is diferent that Checkout Database) and generate an Order Id with ecommercelogistic.order_id_seq database secuence



## Installation

The package can be installed via docker-compose functionality, first donwload the project via github and later execute docker-compose:

```
git clone git@github.com:proyectos-mario/hiberus-ecommerce.git

```
Execute docker-compose

```
docker-compose up --build
```

If you need to stop the process execute:

```
docker-compose rm -f
```

## Swagger Documentation

Once the project had been installed,  This project generate swagger documentation in this links:

- [http://localhost:7002/swagger-ui.html](http://localhost:7002/swagger-ui.html). CheckOut documentation
- [http://localhost:7000/swagger-ui.html](http://localhost:7000/swagger-ui.html). Billing documentation
- [http://localhost:7001/swagger-ui.html](http://localhost:7001/swagger-ui.html). Logistic documentation

# How this work

## localhost urls (Explain why?)

That how you can see, I use localhost for all services. That is because willfully, I expose APIs in localmachine for testing purposes for API services and database services, but internally microservices use its own network for connect its containers  thanks to Docker and Docker-compose technology. A easy test to see it,  is delete expose port in docker-compose file. It can see in docker-compose file like this

```
...
ports: 
      - "7000:7000" 
...
```
With this you can get rest services or connect local to databases

## Abstract design

![alt text](https://github.com/proyectos-mario/hiberus-ecommerce/blob/master/images/designtest.png?raw=true)

## CheckOut Service


First, you should run post service: http://localhost:7002/api/checkout and put in the body something like this:

```json
...
{
  "clientId": 1,
  "date": "2020-07-01T00:19:43.509Z",
  "direction": "Carrera 11 # 140 -52",
  "products": [
    {
      "cost": 100.5,
      "id": 1,
      "quantity": 2
    },
    {
      "cost": 200.3,
      "id": 2,
      "quantity": 3
    }
  ]
} 
...
```

```js
import React from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

// CSS Modules, react-datepicker-cssmodules.css
// import 'react-datepicker/dist/react-datepicker-cssmodules.css';

class Example extends React.Component {
  state = {
    startDate: new Date()
  };

  handleChange = date => {
    this.setState({
      startDate: date
    });
  };

  render() {
    return (
      <DatePicker
        selected={this.state.startDate}
        onChange={this.handleChange}
      />
    );
  }
}
```

## Configuration

The most basic use of the DatePicker can be described with:

```js
<DatePicker selected={this.state.date} onChange={this.handleChange} />
```

You can use `onSelect` event handler which fires each time some calendar date has been selected

```js
<DatePicker
  selected={this.state.date}
  onSelect={this.handleSelect} //when day is clicked
  onChange={this.handleChange} //only when value has changed
/>
```

`onClickOutside` handler may be useful to close datepicker in `inline` mode

See [here](https://github.com/Hacker0x01/react-datepicker/blob/master/docs/datepicker.md) for a full list of props that may be passed to the component. Examples are given on the [main website](https://hacker0x01.github.io/react-datepicker).

### Time picker

You can also include a time picker by adding the showTimeSelect prop

```js
<DatePicker
  selected={this.state.date}
  onChange={this.handleChange}
  showTimeSelect
  dateFormat="Pp"
/>
```

Times will be displayed at 30-minute intervals by default (default configurable via timeIntervals prop)

More examples of how to use the time picker are given on the [main website](https://hacker0x01.github.io/react-datepicker)

### Localization

The date picker relies on [date-fns internationalization](https://date-fns.org/v2.0.0-alpha.18/docs/I18n) to localize its display components. By default, the date picker will use the locale globally set, which is English. Provided are 3 helper methods to set the locale:

- **registerLocale** (string, object): loads an imported locale object from date-fns
- **setDefaultLocale** (string): sets a registered locale as the default for all datepicker instances
- **getDefaultLocale**: returns a string showing the currently set default locale

```js
import { registerLocale, setDefaultLocale } from  "react-datepicker";
import es from 'date-fns/locale/es';
registerLocale('es', es)

<DatePicker
  locale="es"
/>
```

Locales can be changed in the following way:

- **Globally** - `setDefaultLocale('es');`

## Compatibility

### React

We're always trying to stay compatible with the latest version of React. We can't support all older versions of React.

Latest compatible versions:

- React 16 or newer: React-datepicker v2.9.4 and newer
- React 15.5: React-datepicker v2.9.3
- React 15.4.1: needs React-datepicker v0.40.0, newer won't work (due to react-onclickoutside dependencies)
- React 0.14 or newer: All above React-datepicker v0.13.0
- React 0.13: React-datepicker v0.13.0
- pre React 0.13: React-datepicker v0.6.2

### Moment.js

Up until version 1.8.0, this package was using Moment.js. Starting v2.0.0, we switched to using `date-fns`, which uses native Date objects, to reduce the size of the package. If you're switching from 1.8.0 to 2.0.0 or higher, please see the updated example above of check out the [examples site](https://reactdatepicker.com) for up to date examples.

### Browser Support

The date picker is compatible with the latest versions of Chrome, Firefox, and IE10+.

Unfortunately, it is difficult to support legacy browsers while maintaining our ability to develop new features in the future. For IE9 support, it is known that the [classlist polyfill](https://www.npmjs.com/package/classlist-polyfill) is needed, but this may change or break at any point in the future.

## Local Development

The `master` branch contains the latest version of the Datepicker component.

To begin local development:

1. `yarn install`
2. `yarn build-dev`
3. `yarn start`

The last step starts documentation app as a simple webserver on http://localhost:3000.

You can run `yarn test` to execute the test suite and linters. To help you develop the component we’ve set up some tests that cover the basic functionality (can be found in `/tests`). Even though we’re big fans of testing, this only covers a small piece of the component. We highly recommend you add tests when you’re adding new functionality.

### The examples

The examples are hosted within the docs folder and are ran in the simple app that loads the Datepicker. To extend the examples with a new example, you can simply duplicate one of the existing examples and change the unique properties of your example.

## Accessibility

### Keyboard support

- _Left_: Move to the previous day.
- _Right_: Move to the next day.
- _Up_: Move to the previous week.
- _Down_: Move to the next week.
- _PgUp_: Move to the previous month.
- _PgDn_: Move to the next month.
- _Home_: Move to the previous year.
- _End_: Move to the next year.
- _Enter/Esc/Tab_: close the calendar. (Enter & Esc calls preventDefault)

## License

Copyright (c) 2019 HackerOne Inc. and individual contributors. Licensed under MIT license, see [LICENSE](LICENSE) for the full license.
