function mostrarBusyPopup(evt){
  var popup = AdfPage.PAGE.findComponentByAbsoluteId('pt1:pBus');
  if (popup != null) {
    AdfPage.PAGE.addBusyStateListener(popup, handleBusyState);
    evt.preventUserInput();
  }
}

function handleBusyState(evt){
  var popup = AdfPage.PAGE.findComponentByAbsoluteId('pt1:pBus');
  if (popup != null) {
    if (evt.isBusy()) {
      popup.show();
    }
    else if (popup.isPopupVisible()) {
      popup.hide();
      AdfPage.PAGE.removeBusyStateListener(popup, handleBusyState);
    }
  }
}

function initializeDefaultMessageDialogSize(evt){
  var newWidth = evt.getSource().getProperty('defaultMessageDialogContentWidth');
  var newHeight = evt.getSource().getProperty('defaultMessageDialogContentHeight');
  resizeDefaultMessageDialog(newWidth, newHeight);
}

function resizeDefaultMessageDialog(contentWidth, contentHeight){
  var defMDName = 'd1_msgDlg';

  var msgDialogComponent = AdfPage.PAGE.findComponentByAbsoluteId(defMDName);

  if (dimensionIsValid(contentWidth)) {
    msgDialogComponent.setContentWidth(contentWidth);
  }

  if (dimensionIsValid(contentHeight)) {
    msgDialogComponent.setContentHeight(contentHeight);
  }
}

function dimensionIsValid(sampleDimension){
  return (!isNaN(sampleDimension) && sampleDimension > 0);
}