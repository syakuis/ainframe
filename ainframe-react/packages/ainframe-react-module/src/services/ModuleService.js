import * as ModuleDAO from './ModuleDAO';

export const getModuleList = () => ModuleDAO.findAll();
