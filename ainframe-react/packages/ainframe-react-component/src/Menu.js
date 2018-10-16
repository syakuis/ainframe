import React from 'react';
import { Link } from 'react-router-dom';

const Menu = () => (
  <div
    className="nav flex-column nav-pills"
    id="v-pills-tab"
    role="tablist"
    aria-orientation="vertical"
  >
    <Link
      className="nav-link active"
      id="v-pills-home-tab"
      data-toggle="pill"
      to="/inputSearch"
      role="tab"
      aria-controls="v-pills-home"
      aria-selected="true"
    >
      InputSearch
    </Link>
    <a
      className="nav-link"
      id="v-pills-profile-tab"
      data-toggle="pill"
      href="#v-pills-profile"
      role="tab"
      aria-controls="v-pills-profile"
      aria-selected="false"
    >
      Profile
    </a>
    <a
      className="nav-link"
      id="v-pills-messages-tab"
      data-toggle="pill"
      href="#v-pills-messages"
      role="tab"
      aria-controls="v-pills-messages"
      aria-selected="false"
    >
      Messages
    </a>
    <a
      className="nav-link"
      id="v-pills-settings-tab"
      data-toggle="pill"
      href="#v-pills-settings"
      role="tab"
      aria-controls="v-pills-settings"
      aria-selected="false"
    >
      Settings
    </a>
  </div>
);

export default Menu;
