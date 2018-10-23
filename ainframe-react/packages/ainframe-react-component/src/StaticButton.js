import React from 'react';
import PropTypes from 'prop-types';
import { IAdd, IRemove, ISave, IDelete, ISetting } from './Icons';
import Button from './Button';

const propTypes = {
  onlyUseIcon: PropTypes.bool,
  btnMode: PropTypes.string.isRequired,
};

const defaultProps = {
  onlyUseIcon: false,
};

const StaticButton = props => {
  const { onlyUseIcon, btnMode, ...attrs } = props;

  let Icon = null;
  let text = '';
  switch (btnMode) {
    case 'add':
      Icon = <IAdd />;
      text = '추가';
      break;
    case 'remove':
      Icon = <IRemove />;
      text = '제거';
      break;
    case 'save':
      Icon = <ISave />;
      text = '저장';
      break;
    case 'delete':
      Icon = <IDelete />;
      text = '삭제';
      break;
    case 'setting':
      Icon = <ISetting />;
      text = '설정';
      break;
    default:
      Icon = null;
      text = '';
      break;
  }

  return (
    <Button {...attrs}>
      {Icon}
      {onlyUseIcon ? null : ` ${text}`}
    </Button>
  );
};

StaticButton.propTypes = propTypes;
StaticButton.defaultProps = defaultProps;

export const AddButton = props => <StaticButton {...props} btnStyle="success" btnMode="add" />;
export const RemoveButton = props => <StaticButton {...props} btnStyle="danger" btnMode="remove" />;
export const SaveButton = props => <StaticButton {...props} btnStyle="success" btnMode="save" />;
export const DeleteButton = props => <StaticButton {...props} btnStyle="danger" btnMode="delete" />;
export const SettingButton = props => (
  <StaticButton {...props} btnStyle="secondary" btnMode="setting" />
);
